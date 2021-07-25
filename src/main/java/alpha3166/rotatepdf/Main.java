package alpha3166.rotatepdf;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String... args) throws Exception {
        var self = new Main();
        self.execute(args);
    }

    public void execute(String... args) throws Exception {
        var options = new Options();
        options.addOption("h", "display this help and exit");
        options.addOption("f", "Fix rotations to be consistent with reference PDF");

        var cmd = new DefaultParser().parse(options, args);

        if (cmd.hasOption("h")) {
            new HelpFormatter().printHelp("java -jar ROTATEPDF_JAR [OPTION]... REFERENCE_PDF TARGET_PDF",
                    "Show page rotation differences, and fix them if ordered", options, null);
            return;
        }

        if (cmd.getArgs().length != 2) {
            throw new IllegalArgumentException("Reference PDF and target PDF are required");
        }

        var refPath = Paths.get(cmd.getArgs()[0]);
        var targetPath = Paths.get(cmd.getArgs()[1]);

        if (!Files.isRegularFile(refPath)) {
            throw new NoSuchFileException(refPath.toString());
        }
        if (!Files.isRegularFile(targetPath)) {
            throw new NoSuchFileException(targetPath.toString());
        }
        if (refPath.toRealPath().equals(targetPath.toRealPath())) {
            throw new IllegalArgumentException("Reference and target PDFs are the same");
        }

        logger.info(targetPath.toString());
        var targetDirPath = targetPath.toRealPath().getParent(); // toRealPath() prevents bare filename's parent be null
        var newPdfPath = Files.createTempFile(targetDirPath, targetPath.getFileName() + ".", ".pdf");
        var updated = false;
        try (var refPdf = new PdfDocument(new PdfReader(refPath.toFile()));
                var targetPdf = new PdfDocument(new PdfReader(targetPath.toFile()),
                        new PdfWriter(newPdfPath.toFile()))) {

            if (refPdf.getNumberOfPages() != targetPdf.getNumberOfPages()) {
                throw new RuntimeException(String.format("The number of pages differs: %d vs %d",
                        refPdf.getNumberOfPages(), targetPdf.getNumberOfPages()));
            }

            for (int page = 1; page <= refPdf.getNumberOfPages(); page++) {
                int refRotation = refPdf.getPage(page).getRotation();
                int targetRotaion = targetPdf.getPage(page).getRotation();
                if (refRotation != targetRotaion) {
                    updated = true;
                    logger.info(String.format("  p%d: %d -> %d", page, targetRotaion, refRotation));
                    targetPdf.getPage(page).setRotation(refRotation);
                }
            }
        }
        if (cmd.hasOption("f") && updated) {
            Files.move(newPdfPath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } else {
            Files.delete(newPdfPath);
        }
    }
}
