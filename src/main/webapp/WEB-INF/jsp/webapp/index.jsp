<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>webone</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/libs/bootstrap/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/webone/theme.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/webone/menu.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/webone/index.css"/>


    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath }/resources/libs/responsive/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/libs/responsive/respond.min.js"></script>
    <![endif]-->
</head>
<body class="navbar-fixed">
<!--顶部导航-->
<jsp:include page="../common/header.jsp"></jsp:include>

<div id="container" class="container_shadow">
    <div class="main_container">
        <!--左侧栏-sidebar-->
        <div class="sidebar sidebar_fixed">
            <!-- menu-->
            <ul class="list-group tool_border_radius_0 bsMenu" id="menu">
                <li class="list-group-item">
                    <a href="../webapp/index.html" class="active">
                        <span class="glyphicon glyphicon-tower"></span>
                        <span class="menu_text">首页</span>
                    </a>
                </li>
                <li class="list-group-item">
                    <a>
                        <span class="glyphicon glyphicon-pushpin"></span>
                        <span class="menu_text">
                           	webapp布局
                            <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                        </span>
                    </a>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="/plugins/plugin.html" data-ajax="true">插件列表</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" data-ajax="true">第三方插件说明</a>
                        </li>
                    </ul>
                </li>
                <li class="list-group-item">
                    <a>
                        <span class="glyphicon glyphicon-phone"></span>
                        <span class="menu_text">
                            UI组件
                            <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                        </span>
                    </a>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">弹窗</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">树菜单</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">复制插件</a>
                        </li>
                        <li class="list-group-item">
                            <a>
                               	 三级菜单
                                <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                            </a>
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <a href="javascript:;">子项1</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="javascript:;">子项2</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="javascript:;">子项3</a>
                                </li>
                            </ul><!--二级菜单-->
                        </li>
                    </ul><!--二级菜单-->
                </li>
                <li class="list-group-item">
                    <a>
                        <span class="glyphicon glyphicon-th"></span>
                        <span class="menu_text">
                            	表格
                            <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                        </span>
                    </a>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">简单</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" clsss="del">datatable</a>
                        </li>
                    </ul>
                </li>
                <li class="list-group-itemt">
                    <a>
                        <span class="glyphicon glyphicon-edit"></span>
                        <span class="menu_text">
                           	 表单
                            <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                        </span>
                    </a>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">表单组件</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">向导提示 & 验证</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">编辑器</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">文件上传</a>
                        </li>
                    </ul>
                </li>
                <li class="list-group-item">
                    <a>
                        <span class="glyphicon glyphicon-calendar"></span>
                        <span class="menu_text">
                           	 日历
                        </span>
                    </a>
                </li>
                <li class="list-group-item">
                    <a>
                        <span class="glyphicon glyphicon-picture"></span>
                        <span class="menu_text">
                           	 其他页面
                            <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                        </span>
                    </a>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">登录</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">404</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="del">500</a>
                        </li>
                        <li class="list-group-item">
                            <a href="../webapp/easy-pie-chart.html" target="_blank" class="">手机统计页面(easyChart)</a>
                        </li>
                    </ul>
                </li>
            </ul><!--menu-->

            <!-- 缩放控制按钮-->
            <div class="bsMenu_collapse">
                <a href="javascript:void(0);">
                    <span class="glyphicon glyphicon-resize-horizontal"></span>
                </a>
            </div>

        </div><!--sidebar-->

        <!-- 右侧部分-->
        <section class="main_content">
            <jsp:include page="../common/usage.jsp"></jsp:include>
        </section>
    </div><!--main_container-->
    
    <a href="#" class="btn_scroll_up">
        <span class="glyphicon glyphicon-arrow-up"></span>
    </a>
</div>

<script src="${pageContext.request.contextPath }/resources/libs//jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/jquery-layer/layer.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/common/ztool.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/webone/alink.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/webone/menu.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/webone/index.js"></script>
</body>
</html>