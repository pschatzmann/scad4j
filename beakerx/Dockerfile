FROM beakerx/beakerx
MAINTAINER phil schatzmann
USER root
RUN apt-get update && apt-get install -y git curl python3 python3-pip xvfb software-properties-common x11-utils
RUN add-apt-repository ppa:openscad/releases
RUN apt update && apt install -y openscad
ENV PATH /opt/conda/envs/beakerx/bin:$PATH
RUN jupyter labextension install jupyterlab-viewer-3d


