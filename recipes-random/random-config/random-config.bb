LICENSE = "Random_license"
LICENSE_FLAGS = "random_commercial"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d18bf3d6a5024561a9b68c46d56f317"

SRC_URI = " \
   file://LICENSE \
"

PV = "1.0"

S = "${WORKDIR}"

inherit allarch

do_install() {
    # do stuff here
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"

