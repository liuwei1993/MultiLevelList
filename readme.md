# Android RecyclerView 多级列表轻量级实现方案

## 1.实现过程
- 1 将树状数据转化为线性数据
- 2 展示线性数据
- 3 处理点击事件，如果点击父节点则展开或关闭其子节点，并刷新列表（存在性能问题，后期优化）
- 4 已经优化，实现局部刷新

## 2.how to use:

> Step 1. Add the JitPack repository to your build file

```
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
    }
}
```
> Step 2. Add the dependency

```
dependencies {
    compile 'com.github.liuwei1993:MultiLevelList:v1.0'
}
```
- Thanks
