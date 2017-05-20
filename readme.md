# Android RecyclerView 多级列表轻量级实现方案

<img src="https://raw.githubusercontent.com/liuwei1993/MultiLevelList/master/screenshot.jpg" width="300px"/>

## 1.实现过程
- 1 将树状数据转化为线性数据
- 2 展示线性数据
- 3 处理点击事件，如果点击父节点则展开或关闭其子节点，并刷新列表（存在性能问题，后期优化）
- 4 已经优化，实现局部刷新

## 2.使用方法

> Step 1. 添加JitPack repository到你的主项目build.gradle文件

```
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
    }
}
```
> Step 2. 添加依赖

```
dependencies {
    compile 'com.github.liuwei1993:MultiLevelList:v1.0'
}
```
- Thanks
