package com.yly.algorithm.面试日常.杂项;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    假设你的原始数组是一个 List<Region>，每个 Region 对象包含 code（地区编码）、name（地区名称）、parentCode（父地区编码）字段。
    目标是将这些平铺的节点转换为一棵树结构，在 Region 类中添加 List<Region> children 字段。
 */
class Region {
    private String code;
    private String name;
    private String parentCode;
    private List<Region> children = new ArrayList<>();

    // 构造函数
    public Region(String code, String name, String parentCode) {
        this.code = code;
        this.name = name;
        this.parentCode = parentCode;
    }

    // getter 和 setter 方法
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public List<Region> getChildren() {
        return children;
    }

    public void setChildren(List<Region> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Region{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", children=" + children +
                '}';
    }
}

class RegionTreeBuilder {

    public static List<Region> buildRegionTree(List<Region> regions) {
        Map<String, Region> codeToRegionMap = new HashMap<>();
        List<Region> roots = new ArrayList<>();

        // 第一步：将所有 Region 放入 map
        for (Region region : regions) {
            codeToRegionMap.put(region.getCode(), region);
        }

        // 第二步：构造树关系
        for (Region region : regions) {
            String parentCode = region.getParentCode();
            if (parentCode == null || parentCode.isEmpty() || !codeToRegionMap.containsKey(parentCode)) {
                // 根节点
                roots.add(region);
            } else {
                Region parent = codeToRegionMap.get(parentCode);
                parent.getChildren().add(region);
            }
        }

        return roots;
    }
}