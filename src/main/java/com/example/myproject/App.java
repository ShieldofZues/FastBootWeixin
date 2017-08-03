package com.example.myproject;

import com.example.myproject.config.ApiInvoker.ApiInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import com.example.myproject.annotation.WxApplication;
import com.example.myproject.annotation.WxButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello world!
 */
@WxApplication
@Controller
public class App {

    @Autowired
    ApiInvoker apiInvoker;

    //用mvn命令执行和直接执行该Java是一样的结果，mvn spring-boot:run是找到这个文件的main去执行的
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        return apiInvoker.getCallbackIp();
    }

    @RequestMapping("menu")
    @ResponseBody
    public String menu() {
        return apiInvoker.getMenu();
    }

    /**
     * 一次选择位置共推送了以下事件：
     * 1、msgType:event-eventType:location_select事件，菜单主动触发
     * 2、msgType:location类型，主动推送，用户发送的消息
     * 3、msgType:event-eventType:location事件 系统事件推送
     */

    @WxButton(group = WxButton.Group.LEFT, main = true, name = "一级菜单左", key = "left")
    public void left() {
    }

    @WxButton(group = WxButton.Group.MIDDLE, main = true, name = "一级菜单中", key = "middle")
    public void middle() {
    }

    @WxButton(group = WxButton.Group.RIGHT, main = true, name = "一级菜单右", key = "right")
    public void right() {
    }

    @WxButton(type = WxButton.Type.CLICK, group = WxButton.Group.LEFT, order = WxButton.Order.FIRST, name = "二级菜单左一", key = "left_1")
    public void click() {
    }

    @WxButton(type = WxButton.Type.LOCATION_SELECT, group = WxButton.Group.LEFT, order = WxButton.Order.SECOND, name = "二级菜单左二", key = "left_2")
    public void location() {
    }

    //    @WxButton(type = WxButton.Type.MEDIA_ID, mediaId = "1", group = WxButton.Group.LEFT, name = "二级菜单左三", key = "left_3")
    public void media() {
    }

    @WxButton(type = WxButton.Type.PIC_PHOTO_OR_ALBUM, group = WxButton.Group.LEFT, order = WxButton.Order.FORTH, name = "二级菜单左四", key = "left_4")
    public void pic() {
    }

    @WxButton(type = WxButton.Type.PIC_SYSPHOTO, group = WxButton.Group.LEFT, order = WxButton.Order.FIFTH, name = "二级菜单左五", key = "left_5")
    public void picSys() {
    }

    @WxButton(type = WxButton.Type.PIC_WEIXIN, group = WxButton.Group.MIDDLE, order = WxButton.Order.FIRST, name = "二级菜单中一", key = "middle_1")
    public void picWeixin() {
    }

    @WxButton(type = WxButton.Type.SCANCODE_PUSH, group = WxButton.Group.MIDDLE, order = WxButton.Order.SECOND, name = "二级菜单中二", key = "middle_2")
    public void scanCode() {
    }

    @WxButton(type = WxButton.Type.SCANCODE_WAITMSG, group = WxButton.Group.MIDDLE, order = WxButton.Order.THIRD, name = "二级菜单中三", key = "middle_3")
    public void scanCodeWait() {
    }

    @WxButton(type = WxButton.Type.VIEW, url = "http://baidu.com", group = WxButton.Group.MIDDLE, order = WxButton.Order.FORTH, name = "二级菜单中四", key = "middle_4")
    public void view() {
    }

    //    @WxButton(type = WxButton.Type.VIEW_LIMITED, group = WxButton.Group.MIDDLE, name = "二级菜单中五", key = "middle_5")
    public void viewLimited() {
    }

    @WxButton(group = WxButton.Group.RIGHT, name = "二级菜单右一", key = "right_1")
    public void c1() {
    }

}
