package com.yyfly.common.util;
/**
 * 系统工具类
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
public class OSUtils {
    private static String OS = System.getProperty("os.name").toLowerCase();

    private static OSUtils _instance = new OSUtils();

    private OSPlatform platform;

    private OSUtils() {
    }

    public static boolean isLinux() {
        return OS.contains("linux");
    }

    public static boolean isMacOS() {
        return OS.contains("mac") && OS.contains("os") && OS.contains("x");
    }

    public static boolean isMacOSX() {
        return OS.contains("mac") && OS.contains("os") && OS.contains("x");
    }

    public static boolean isWindows() {
        return OS.contains("windows");
    }

    public static boolean isOS2() {
        return OS.contains("os/2");
    }

    public static boolean isSolaris() {
        return OS.contains("solaris");
    }

    public static boolean isSunOS() {
        return OS.contains("sunos");
    }

    public static boolean isMPEiX() {
        return OS.contains("mpe/ix");
    }

    public static boolean isHPUX() {
        return OS.contains("hp-ux");
    }

    public static boolean isAix() {
        return OS.contains("aix");
    }

    public static boolean isOS390() {
        return OS.contains("os/390");
    }

    public static boolean isFreeBSD() {
        return OS.contains("freebsd");
    }

    public static boolean isIrix() {
        return OS.contains("irix");
    }

    public static boolean isDigitalUnix() {
        return OS.contains("digital") && OS.contains("unix");
    }

    public static boolean isNetWare() {
        return OS.contains("netware");
    }

    public static boolean isOSF1() {
        return OS.contains("osf1");
    }

    public static boolean isOpenVMS() {
        return OS.contains("openvms");
    }

    /**
     * 获取操作系统名字
     *
     * @return 操作系统名
     */
    public static OSPlatform getOSname() {
        if (isAix()) {
            _instance.platform = OSPlatform.AIX;
        } else if (isDigitalUnix()) {
            _instance.platform = OSPlatform.Digital_Unix;
        } else if (isFreeBSD()) {
            _instance.platform = OSPlatform.FreeBSD;
        } else if (isHPUX()) {
            _instance.platform = OSPlatform.HP_UX;
        } else if (isIrix()) {
            _instance.platform = OSPlatform.Irix;
        } else if (isLinux()) {
            _instance.platform = OSPlatform.Linux;
        } else if (isMacOS()) {
            _instance.platform = OSPlatform.Mac_OS;
        } else if (isMacOSX()) {
            _instance.platform = OSPlatform.Mac_OS_X;
        } else if (isMPEiX()) {
            _instance.platform = OSPlatform.MPEiX;
        } else if (isNetWare()) {
            _instance.platform = OSPlatform.NetWare_411;
        } else if (isOpenVMS()) {
            _instance.platform = OSPlatform.OpenVMS;
        } else if (isOS2()) {
            _instance.platform = OSPlatform.OS2;
        } else if (isOS390()) {
            _instance.platform = OSPlatform.OS390;
        } else if (isOSF1()) {
            _instance.platform = OSPlatform.OSF1;
        } else if (isSolaris()) {
            _instance.platform = OSPlatform.Solaris;
        } else if (isSunOS()) {
            _instance.platform = OSPlatform.SunOS;
        } else if (isWindows()) {
            _instance.platform = OSPlatform.Windows;
        } else {
            _instance.platform = OSPlatform.Others;
        }
        return _instance.platform;
    }

    enum OSPlatform {
        /**
         * any
         */
        Any("any"),
        Linux("Linux"),
        Mac_OS("Mac OS"),
        Mac_OS_X("Mac OS X"),
        Windows("Windows"),
        OS2("OS/2"),
        Solaris("Solaris"),
        SunOS("SunOS"),
        MPEiX("MPE/iX"),
        HP_UX("HP-UX"),
        AIX("AIX"),
        OS390("OS/390"),
        FreeBSD("FreeBSD"),
        Irix("Irix"),
        Digital_Unix("Digital Unix"),
        NetWare_411("NetWare"),
        OSF1("OSF1"),
        OpenVMS("OpenVMS"),
        Others("Others");

        private OSPlatform(String desc) {
            this.description = desc;
        }

        private String description;
    }

}
