# NewsDemo
本人用于练习所做的一个新闻 DEMO 。
通过解析 Json 数据来显示新闻。


# 已知BUG
在刷新期间，向下滑动列表会出现 bug。
原因是在刷新发送了网络请求期间，RecycleView 的列表此时被清空， 数组此时为 0 ， 而此时滑动列表就会出现 index 不为 0 的情况。

##解决办法
在刷新时，数组清空的之后，添加 `adapter.notifyDataSetChanged()` 来更新列表即可。
```
newsList.clear();
adapter.notifyDataSetChanged();
getJsonDate(url);

```

