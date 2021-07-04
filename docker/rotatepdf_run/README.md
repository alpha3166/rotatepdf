# Usage

## Setup

[Build](../rotatepdf_dev/README.md) first.

Replace UID (`1000`) and GID (`1000`) in `Dockerfile` and `docker-compose.yml` to the ones you are using.

Put target PDF files in this directory.

## To Run

    docker-compose run --rm ws ref.pdf target.pdf

or

    docker build -f Dockerfile -t rotatepdf ../..
    docker run -it --rm -u $(id -u):$(id -g) -v $PWD:/workspace -w /workspace rotatepdf ref.pdf target.pdf
