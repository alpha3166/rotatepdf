# Usage

## Setup

Replace UID (`1000`) and GID (`1000`) in `Dockerfile` and `docker-compose.yml` with yours.

Put target PDF files in this directory.

## With Docker

To build image:

    docker build -f Dockerfile -t rotatepdf-cli ../..

To run:

    docker run -it --rm -u $(id -u):$(id -g) -v $PWD:/workspace -w /workspace rotatepdf-cli ref.pdf target.pdf

## With Docker-Compose

To run:

    docker-compose run --rm ws ref.pdf target.pdf
