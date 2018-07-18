<%--
  Created by IntelliJ IDEA.
  User: MackyHuang
  Date: 2018/7/18
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="BULUSTAR-index">
    <meta name="author" content="Mackyhuang">
    <link rel="icon" href="../../favicon.ico">

    <title>用户管理</title>

    <link href="./static/teach/css/bootstrap.min.css" rel="stylesheet">
    <link href="./static/teach/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="./static/teach/css/dashboard.css" rel="stylesheet">
    <link href="./static/teach/css/mycss.css" rel="stylesheet">

</head>

<body>
<!-- 顶部导航栏 -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" id="huge">蓝星苑</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">
                    <span class="glyphicon glyphicon-user"></span>
                    &nbsp;
                    <span class="" id="username">Mackyhuang</span>
                    <span class="">，欢迎您</span></a></li>
                <li><a href="staff_info.do">个人资料</a></li>
                <li><a href="#" id="logout">注销</a></li>
                <li><a href="#">帮助</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- 舞台 -->
<div class="container-fluid">
    <div class="row">
        <!-- 左侧导航栏 -->
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="nav nav-sidebar">
                <li class="text-center bord"><a href="#"><strong>功能一览</strong> <span class="sr-only">(current)</span></a></li>
            </div>
            <ul class="nav nav-sidebar" id="nav-list">

            </ul>
        </div>

        <!-- 右侧模块 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">咨询管理</h1>

            <div class="span12" id="jump-menu">
                <div class="tabbable" id="tabs-user">
                    <div class="nav nav-tabs">
                        <!-- 标签1 -->
                        <div class="active">
                            <a href="#panel-1" data-toggle="tab" class="col-xs-4 col-sm-4 placeholders" id="show-role" style='text-decoration:none;'>
                                <h4>查看咨询列表</h4>
                                <span class="text-muted">点击查看当前咨询详细</span>
                            </a>
                        </div>
                    </div>
                    <!-- 标签1对应的模块 列表 -->
                    <div class="tab-content">
                        <div class="tab-pane active" id="panel-1">
                            <h2 class="sub-header">咨询列表</h2>
                            <form action="information_list.do", method="get">
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="which-input" class="">标题</label>
                                    <input name="title" type="text" class="form-control" placeholder="请输入用户名或姓名" id="which-input" value="${title}">
                                </div>
                                <div class="col-sm-4">
                                    <label for="which-stage" class="">类型</label>
                                    <select name="statu" class="form-control" id="which-stage" style="width:100%" selec="${statu}">
                                        <option value ="-1">全部</option>
                                        <option value ="1">正常</option>
                                        <option value ="2">上架</option>
                                        <option value ="3">无效</option>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <br/>
                                    <input type="submit" class="btn btn-primary text-center center-block " id="submit-which-stage" value="查询">
                                </div>
                            </div>
                            </form>
                            <div class="row">
                                <br>
                                <div class="col-sm-3">
                                    <button href="#panel-3" data-toggle="tab" class="btn btn-warning " id="submit-add">添加</button>
                                </div>
                                <br>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-hover" id="role-table">
                                    <thead>
                                    <tr>
                                        <th>标题</th>
                                        <th>作者</th>
                                        <th>采编人</th>
                                        <th>发布时间</th>
                                        <th>采编时间</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${informationList }" var="information">
                                    <tr>
                                        <td>${information.informationTitle}</td>
                                        <td>${information.informationAuthor}</td>
                                        <td>${information.informationCreateUser}</td>
                                        <td>
                                            <fmt:formatDate value="${information.informationPublishTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${information.informationCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                        </td>
                                        <td>${information.informationStatu}</td>
                                        <td>
                                            <button class="delete-role-btn btn btn-danger pull-right" href="#panel-4" data-toggle="tab" informationId="'+ ${information.informationId} +'" role="button">
                                                删除
                                            </button>
                                            <button class="update-role-btn btn btn-primary pull-right pre-update-button" href="#panel-2" data-toggle="tab" informationId="'+ ${information.informationId} +'" role="button">
                                                修改
                                            </button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- 标签2对应的模块  修改-->
                        <div class="tab-pane col-sm-12" id="panel-2">

                            <h2 class="sub-header">修改标题</h2>
                            <form class="form" onsubmit="return ">
                                <div class="form-group" id="rolename-change">
                                    <label for="insert-rolename" class="sr-only ">用户名</label>
                                    <input type="text" maxlength="63" id="update-rolename" style="width:200px;" class="form-control center-block" placeholder="请输入需要修改的角色名" required="" autofocus=""  readonly="readonly">
                                    <span class="help-block center-block" style="text-align: center;" id="update-rolename-help"></span>
                                </div>
                                <div id="select-box-update">

                                </div>
                                <button class="btn btn-lg btn-primary btn-block center-block" id="submit-update-role" style="width:200px;">修改</button>
                            </form>


                        </div>
                        <!-- 标签3对应的模块  模块-->
                        <div class="tab-pane col-sm-12" id="panel-3">
                            <h2 class="sub-header">新增标题</h2>
                            <form class="form" onsubmit="return ">
                                <div class="form-group" id="rolename-create">
                                    <label for="insert-rolename" class="sr-only">角色名</label>
                                    <input type="text" maxlength="63" id="insert-rolename" style="width:200px;" class="form-control center-block" placeholder="给新的角色起个名字把" required="" autofocus="">
                                    <span class="help-block" id="insert-rolename-help"></span>
                                </div>
                                <div id="select-box-add">

                                </div>
                                <button class="btn btn-lg btn-primary btn-block center-block" id="submit-create-role" style="width:200px;">创建</button>
                            </form>

                        </div>
                        <!-- 标签4对应的模块 -->
                        <div class="tab-pane col-sm-5 col-sm-offset-3" id="panel-4">
                            <h2 class="sub-header">删除标题</h2>
                            <form class="form" onsubmit="return false">
                                <div class="form-group" id="role-delete" style="margin-top:50px">
                                    <label for="delete-role-input" class="sr-only">角色名</label>
                                    <input type="text" maxlength="63" id="delete-role-input" class="form-control" placeholder="输入需要删除的角色名" required=""  readonly="readonly">
                                    <span class="help-block" id="delete-user-help"></span>
                                </div>
                                <!-- 遮罩窗体确定是否操作 -->
                                <!-- 触发按钮 -->
                                <button id="modal-role-delete-a" href="#modal-delete-block" role="button" class="btn btn-lg btn-primary btn-block" data-toggle="modal" style="margin-top:50px">
                                    删除
                                </button>
                                <!-- 被隐藏的模块 -->
                                <div class="modal fade" id="modal-delete-block" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                <h4 class="modal-title " id="myModalLabel">
                                                    确定要删除这个角色以及权限吗？
                                                </h4>
                                            </div>
                                            <div class="modal-footer">
                                                <button class="btn btn-danger" id="delete-role-button">删除</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<script src="./static/teach/js/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="./static/teach/js/jquery.min.js"><\/script>')</script>
<script src="./static/teach/js/bootstrap.min.js"></script>
<script src="./static/teach/js/holder.min.js"></script>
<script src="./static/teach/js/ie10-viewport-bug-workaround.js"></script>
<script src="./static/teach/js/general.js"></script>
<script src="./static/information/js/information.js"></script>
</body>
</html>
