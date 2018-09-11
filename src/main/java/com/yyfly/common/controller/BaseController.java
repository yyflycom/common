package com.yyfly.common.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

/**
 * 基础Controller
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@RestController
public class BaseController {

    private static Logger logger = LogManager.getLogger(BaseController.class);

    protected static String PARAM_DRAW = "draw";

    protected static String PARAM_RECORDS_TOTAL = "recordsTotal";

    protected static String PARAM_RECORDS_FILTERED = "recordsFiltered";

    protected static String PARAM_DATA = "data";

    protected static String PARAM_DTO = "dto";

    /**
     * 数据绑定
     *
     * @param binder the binder
     * @author : yangjunqing / 2018-06-05
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        PropertyEditor editor = PropertyEditorManager.findEditor(String.class);
        binder.registerCustomEditor(String.class, "password", editor);
    }


    /**
     * 构建返回页面路径
     * getPagePath("", "", "list") ===> list
     *
     * @param page the page
     * @return the string
     * @author : yyfly / 2018-08-08
     */
    public static String getPagePath(String page) {
        return getPagePath("", "", page);
    }

    /**
     * 构建返回页面路径
     * getPagePath("", "user", "list") ===> user/list
     *
     * @param moduleName the module name
     * @param page       the page
     * @return the string
     * @author : yyfly / 2018-08-08
     */
    public static String getPagePath(String moduleName, String page) {
        return getPagePath("", moduleName, page);
    }

    /**
     * 构建返回页面路径
     * getPagePath("system", "user", "list") ===> system/user/list
     *
     * @param prefix     前缀
     * @param moduleName 模块名称
     * @param page       页面名称
     * @return the string
     * @author : yyfly / 2018-08-08
     */
    public static String getPagePath(String prefix, String moduleName, String page) {
        return buildString(prefix, moduleName, page, "/");
    }

    /**
     * 构建权限标识
     * getShiroPermission("", "", "list") ===> user:list
     *
     * @param permission 权限标识
     * @return the string
     * @author : yyfly / 2018-08-08
     */
    public static String getPermissions(String permission) {
        return getPermissions("", "", permission);
    }

    /**
     * 构建权限标识
     * getShiroPermission("", "user", "list") ===> user:list
     *
     * @param moduleName 模块名称
     * @param permission 权限标识
     * @return the string
     * @author : yyfly / 2018-08-08
     */
    public static String getPermissions(String moduleName, String permission) {
        return getPermissions("", moduleName, permission);
    }

    /**
     * 构建权限标识
     * getShiroPermission("system", "user", "list") ===> system:user:list
     *
     * @param prefix     前缀
     * @param moduleName 模块名称
     * @param permission 权限标识
     * @return the string
     * @author : yyfly / 2018-08-08
     */
    public static String getPermissions(String prefix, String moduleName, String permission) {
        return buildString(prefix, moduleName, permission, ":");
    }

    /**
     * 构建国际化Message Key
     * getEntityMessage("", "user", "list") ===> user.list
     *
     * @param moduleName 模块名称
     * @param message    信息
     * @return the string
     * @author : yyfly / 2018-08-08
     */
    public static String getEntityMessage(String moduleName, String message) {
        return getEntityMessage("", moduleName, message);
    }

    /**
     * 构建国际化Message Key
     * getEntityMessage("system", "user", "list") ===> system.user.list
     *
     * @param prefix     前缀
     * @param moduleName 模块名称
     * @param message    信息
     * @return the string
     * @author : yyfly / 2018-08-08
     */
    public static String getEntityMessage(String prefix, String moduleName, String message) {
        return buildString(prefix, moduleName, message, ".");
    }

    /**
     * 根据不同功能拼接字符串
     * path 路径 ==> system/user/list
     * 权限标识  ==> system:user:list
     * 国际化    ==> system.user.list
     *
     * @param prefix     前缀，通常是模块集合，比如安全模块下面包括用户、资源、角色等
     * @param moduleName 模块名称，比如用户
     * @param value      业务对应的名称，比如用户列表，相应的网页路径、权限标识、国际化都是list
     * @param split      分割符号，页面 : "/"   权限标识 : ":"  国际化: "."
     * @return the string
     * @author : yyfly / 2018-08-08
     */
    public static String buildString(String prefix, String moduleName, String value, String split) {

        StringBuilder entityPath = new StringBuilder();
        if (StringUtils.isNotBlank(prefix)) {
            entityPath.append(prefix);
        }
        if (StringUtils.isNotBlank(prefix)) {
            if(StringUtils.isNotBlank(moduleName) || StringUtils.isNotBlank(value)){
                entityPath.append(split);
            }
        }
        if (StringUtils.isNotBlank(moduleName)) {
            entityPath.append(moduleName);
        }
        if (StringUtils.isNotBlank(moduleName) && StringUtils.isNotBlank(value)) {
            entityPath.append(split);
        }
        if (StringUtils.isNotBlank(value)) {
            entityPath.append(value);
        }
        return entityPath.toString();
    }

    /**
     * 添加页面公共参数
     *
     * @param <T>        the type parameter
     * @param moduleName 模块
     * @param model      the model
     * @author : yyfly / 2017-04-22
     */
    protected <T> void addEntityParam(String moduleName, Model model) {
        addEntityParam("", moduleName, model);
    }

    /**
     * 添加页面公共参数
     *
     * @param <T>        the type parameter
     * @param prefix     前缀，通常是模块集合，比如安全模块下面包括用户、资源、角色等
     * @param moduleName 模块
     * @param model      the model
     * @author : yyfly / 2017-04-22
     */
    protected <T> void addEntityParam(String prefix, String moduleName, Model model) {
        addEntityParam(prefix, moduleName, "", model);
    }

    /**
     * 添加页面公共参数
     *
     * @param <T>        the type parameter
     * @param prefix     前缀，通常是模块集合，比如安全模块下面包括用户、资源、角色等
     * @param moduleName 模块
     * @param function   功能
     * @param model      the model
     * @author : yyfly / 2017-04-22
     */
    protected <T> void addEntityParam(String prefix, String moduleName, String function, Model model) {

        String entityPath = getPagePath(prefix, moduleName, function);
        String entityShiro = getPermissions(prefix, moduleName, function);
        String entityMessage = getEntityMessage(prefix, moduleName, function);

        //Bread crumb 列表界面标题
        model.addAttribute("moduleName", moduleName);
        //path封装
        model.addAttribute("entityPath", entityPath);
        //权限
        model.addAttribute("entityShiro", entityShiro);
        //国际化
        model.addAttribute("prefixMessage", prefix);

        model.addAttribute("entityMessage", entityMessage);

        logger.error("entityPath = " + entityPath + " || entityShiro = " + entityShiro + " || entityMessage = " + entityMessage);
    }

    /**
     * 添加页面公共参数
     *
     * @param <T>                the type parameter
     * @param moduleName         模块
     * @param entityPath         路径
     * @param entityPermission   权限标识
     * @param entityMessage      国际化
     * @param model              the model
     * @author : yyfly / 2017-04-22
     */
    protected <T> void addEntityParam(String moduleName, String entityPath, String entityPermission, String entityMessage, Model model) {
        //Bread crumb 列表界面标题
        model.addAttribute("moduleName", moduleName);
        //path封装
        model.addAttribute("entityPath", entityPath);
        //权限
        model.addAttribute("entityPermission", entityPermission);
        //国际化
        model.addAttribute("entityMessage", entityMessage);

        logger.error("entityPath = " + entityPath + " || entityPermission = " + entityPermission + " || entityMessage = " + entityMessage);
    }

}
