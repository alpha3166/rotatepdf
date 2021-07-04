# RotatePDF

OCRで回転されたPDFページの向きを復元する

## RotatePDFとは

RotatePDFは、2つのPDFファイルの各ページの向きを比べ、差異があればその情報を表示します。-fオプションを指定して実行すると、差異があるページの向きを、元のPDFと同じになるよう補正します。

Adobe AcrobatでPDFにOCRをかけると、文字が横を向いているページが自動的に回転させられますが、それを元に戻す場合に使用します。

## クイックスタート

1. Java (11以降) をインストールします。

2. [Releaseページ](https://github.com/alpha3166/rotatepdf/releases)から`rotatepdf-1.0.0-jar-with-dependencies.jar`をダウンロードします。

3. コマンドラインから、元のPDFと対象のPDFを引数に指定して、JARを実行します。ページの向きの違いが表示されます。

       java -jar rotatepdf-1.0.0-jar-with-dependencies.jar ref.pdf target.pdf

   向きの違いを修正するには、-fオプションを追加します。

       java -jar rotatepdf-1.0.0-jar-with-dependencies.jar -f ref.pdf target.pdf

## コマンドラインオプション

    使い方: java -jar ROTATEPDF_JAR [OPTION]... REFERENCE_PDF TARGET_PDF
    ページの向きの違いを表示し、指示された場合は修正する
     -f         元のPDFにあわせて向きを修正します
     -h         このヘルプを表示して終了します

## RotatePDFのビルド方法

Git、Java (11以降)、Maven をインストールし、クローン&ビルドします。

    git clone https://github.com/alpha3166/rotatepdf
    cd rotatepdf
    mvn package

## DockerでRotatePDFを使うには

[docker/rotatepdf_run](docker/rotatepdf_run)ディレクトリ内の[README.md](docker/rotatepdf_run/README.md)を参照してください。
