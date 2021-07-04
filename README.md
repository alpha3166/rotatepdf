# RotatePDF

Restore PDF page rotations that have been changed by OCR

## What is RotatePDF

RotatePDF compares the rotation of the pages of two PDF files and displays information about any differences. When run with the -f option, it will correct the rotation of the differing pages to be the same as the original PDF.

When you apply OCR to a PDF with Adobe Acrobat, pages with sideways text are automatically rotated, and you can use RotatePDF to undo the rotation.

## Quick Start

1. Install Java (11 or later).

2. Download `rotatepdf-1.0.0-jar-with-dependencies.jar` from [Release page](https://github.com/alpha3166/rotatepdf/releases).

3. Execute the JAR from the command line, specifying the original PDF and the target PDF as arguments. The page rotation differences will be displayed.

       java -jar rotatepdf-1.0.0-jar-with-dependencies.jar ref.pdf target.pdf

   To fix the rotation differences, add -f option.

       java -jar rotatepdf-1.0.0-jar-with-dependencies.jar -f ref.pdf target.pdf

## Command-Line Options

    usage: java -jar ROTATEPDF_JAR [OPTION]... REFERENCE_PDF TARGET_PDF
    Show page rotation differences, and fix them if ordered
     -f   Fix rotations to be consistent with reference PDF
     -h   display this help and exit

## How to build RotatePDF

Install Git, Java (11 or later), and Maven, then clone & build.

    git clone https://github.com/alpha3166/rotatepdf
    cd rotatepdf
    mvn package

## How to use RotatePDF with Docker

See the [README.md](docker/rotatepdf_run/README.md) in [docker/rotatepdf_run](docker/rotatepdf_run) directory.
