# 前端protobuf入门
> 此文只讲述web前端与后端使用protobuf进行数据交互的基础与入门教学，更加详细的内容请查看google protobuf<a href="https://developers.google.com/protocol-buffers/">官方文档</a>以及protobuf项目<a href="https://github.com/google/protobuf">github地址</a>

### 准备工作
> 1.下载protobuf编译器，<a href="https://github.com/google/protobuf/releases">下载地址</a>, 我的编程环境的Windows平台，所以这里找到并下载protoc-3.5.1-win32.zip，解压出来放到一个你希望放的地方，我这里放在D盘的根目录

> 2.配置环境变量，配置环境变量只是为了方便命令行编译，所以不一定要配置，环境变量配置到bin目录就好了

> 3.新建一个测试用的.proto文件
```
// person.proto  文件
// package 请与需要使用语言的package目录一致

syntax = "proto3";
package com.etertops.protos;

message PersonMessage{
    string id = 1;
    string name = 2;
    string sex = 3;
    string address = 4;
    int32 age = 5;
    string phone = 6;
}
```
> 4.编译文件： protoc --java_out=输出目录 需要编译的文件
> 比如我在文件目录使用protoc --java_out=./ person.proto 编译person.proto编译到当前目录，文件


