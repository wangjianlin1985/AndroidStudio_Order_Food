package cn.itfxq.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {
    private Long id;
    private String name;
    private String url;
    private String icon;
    private Long pid;//父id
    private Menu parent;
    private List<Menu> childs = new ArrayList();
    private Permission permission;//菜单对应的权限
}
