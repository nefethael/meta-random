SUMMARY = "Network configuration tool using YAML"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/CanonicalLtd/netplan;protocol=https \
	file://0001-Replace-hardcoded-python3-path.patch \
	file://0002-Fix-Makefile-for-crosscompilation.patch \
	file://0003-Change-default-generate-path.patch \
    file://10-default.yaml \
"

PV = "0.34.1+git${SRCPV}"
SRCREV = "0bf20a4c79ac98abb6576929af661cd3bed66302"

S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 libyaml"
RDEPENDS_${PN} += "python3 python3-pyyaml python3-netifaces"

inherit pkgconfig systemd python3-dir

#SYSTEMD_SERVICE_${PN} = "netplan-wpa@.service"

do_compile () {
	oe_runmake generate
}

do_install () {

	install -d ${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/commands
  	install -m 0755 ${S}/generate ${D}${PYTHON_SITEPACKAGES_DIR}/netplan/generate
   	install -m 0755 ${S}/src/netplan.script ${D}${PYTHON_SITEPACKAGES_DIR}/netplan/netplan.script
    	
	install -Dm 644 ${S}/netplan/terminal.py			${D}${PYTHON_SITEPACKAGES_DIR}/netplan/terminal.py
	install -Dm 644 ${S}/netplan/__init__.py			${D}${PYTHON_SITEPACKAGES_DIR}/netplan/__init__.py
	install -Dm 644 ${S}/netplan/configmanager.py			${D}${PYTHON_SITEPACKAGES_DIR}/netplan/configmanager.py
	install -Dm 644 ${S}/netplan/cli/__init__.py			${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/__init__.py
	install -Dm 644 ${S}/netplan/cli/core.py			${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/core.py
	install -Dm 644 ${S}/netplan/cli/commands/generate.py		${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/commands/generate.py
	install -Dm 644 ${S}/netplan/cli/commands/__init__.py		${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/commands/__init__.py
	install -Dm 644 ${S}/netplan/cli/commands/migrate.py		${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/commands/migrate.py
	install -Dm 644 ${S}/netplan/cli/commands/ip.py			${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/commands/ip.py
	install -Dm 644 ${S}/netplan/cli/commands/apply.py		${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/commands/apply.py
	install -Dm 644 ${S}/netplan/cli/commands/try_command.py	${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/commands/try_command.py
	install -Dm 644 ${S}/netplan/cli/utils.py			${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/utils.py
        
	install -d -m 0755 ${D}${bindir}
    ln -s ../../../${PYTHON_SITEPACKAGES_DIR}/netplan/netplan.script ${D}${bindir}/netplan

 	install -d ${D}${systemd_unitdir}/system-generators
    ln -s ../../../${PYTHON_SITEPACKAGES_DIR}/netplan/generate ${D}${systemd_unitdir}/system-generators/netplan
  
  	#install -d ${D}${systemd_unitdir}/system/
	#install -D -m 644 ${S}/src/netplan-wpa@.service ${D}${systemd_unitdir}/system/netplan-wpa@.service
}

do_install_append() {
        # config
        install -d ${D}${sysconfdir}/netplan
        install -m 0644 ${WORKDIR}/10-default.yaml ${D}${sysconfdir}/netplan
}

FILES_${PN} += "${PYTHON_SITEPACKAGES_DIR}/netplan ${systemd_unitdir}/system-generators/netplan"
