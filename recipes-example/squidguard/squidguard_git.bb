# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   COPYING
#   src/patricia/copyright
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://GPL;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://COPYING;md5=17cccb55725bad30d60ee344fa9561e6 \
                    file://src/patricia/copyright;md5=24ce00462592cdf0cefaa44c3e1449f3"

SRC_URI = "git://github.com/andihofmeister/squidGuard;protocol=https"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "c10e642ebcd03c86cda3e571807d67c99471e8e2"

S = "${WORKDIR}/git"

# NOTE: the following prog dependencies are unknown, ignoring: lynx
# NOTE: the following library dependencies are unknown, ignoring: ldap
#       (this is based on recipes that have previously been built and packaged)
DEPENDS = "virtual/db openldap mysql5 zlib flex"

SRC_URI += " \
    file://fix-misleading-indentation.patch \
"

# NOTE: if this software is not capable of being built in a separate build directory
# from the source, you should replace autotools with autotools-brokensep in the
# inherit line
inherit autotools-brokensep

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF += " \
    --with-squiduser=nobody \
    --with-db=${STAGING_INCDIR}/.. \
    --with-sg-config=${sysconfdir}/squid/squidGuard.conf \
    --with-sg-logdir=${localstatedir}/log/squid \
    --with-sg-dbhome=${localstatedir}/lib/squidguard/db \
    --with-ldap=yes \
    --with-ldap-inc=${STAGING_INCDIR} \
    --with-ldap-lib=${STAGING_LIBDIR} \
    --with-mysql=${STAGING_INCDIR}/.. \
"

