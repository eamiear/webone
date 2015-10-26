<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>webone</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/libs/bootstrap/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/font-awesome-4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/libs/laycode/laycode.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/libs/jquery-layer/skin/layer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/webone/theme.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/webone/menu.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/theme/sunset.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/webone/list.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/webone/index.css"/>


    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath }/resources/libs/responsive/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/libs/responsive/respond.min.js"></script>
    <![endif]-->
</head>
<body class="navbar-fixed">
<!-- 头部导航 -->
<jsp:include page="../common/header.jsp"></jsp:include>

<div id="container" class="container_shadow">
    <div class="main_container">
        <!--左侧栏-sidebar-->
        <div class="sidebar sidebar_fixed">
            <!-- menu-->
            <ul class="list-group tool_border_radius_0 bsMenu" id="menu">
                <li class="list-group-item">
                    <a href="index.html" class="active">
                        <span class="glyphicon glyphicon-tower"></span>
                        <span class="menu_text">首页</span>
                    </a>
                </li>
                <li class="list-group-item">
                    <a>
                        <span class="glyphicon glyphicon-pushpin"></span>
                        <span class="menu_text">
                           	 插件列表
                            <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                        </span>
                    </a>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="../frontend/plugin.html" data-ajax="true">插件列表</a>
                        </li>
                        <li class="list-group-item">
                            <a href="pluginsDocs.html" data-ajax="true">第三方插件说明</a>
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
                            <a class="">
                            	弹窗
                            	<span class="glyphicon glyphicon-chevron-down pull-right"></span>
                            </a>
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <a href="javascript:;">layer</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="javascript:;">子项2</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="javascript:;">子项3</a>
                                </li>
                            </ul><!--三级菜单-->
                        </li>
                        <li class="list-group-item">
                            <a class="">树菜单</a>
                        </li>
                        <li class="list-group-item">
                            <a  class="del">复制插件</a>
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
                            </ul><!--三级菜单-->
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
                            <a href="javascript:;" class="">简单Table</a>
                        </li>
                        <li class="list-group-item">
                            <a href="../frontend/dataTable.html" data-ajax="true" class="">DataTable</a>
                        </li>
                        <li class="list-group-item">
                            <a class="">BootstrapTable
                            	<span class="glyphicon glyphicon-chevron-down pull-right"></span>
                            </a>
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <a href="../frontend/bsTable-by-js" data-ajax="true">JS启用bsTable</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="../frontend/bsTable-by-tag" data-ajax="true">标签驱动bsTable</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="../frontend/bsTable-by-js-tag" data-ajax="true">Js、标签驱动bsTable</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="../frontend/bsTable-common-usage" data-ajax="true">bsTable常用功能</a>
                                </li>
                            </ul><!--二级菜单-->
                        </li>
                    </ul>
                </li>
                <li class="list-group-item">
                	<a>
                        <span class="glyphicon glyphicon-stats"></span>
                        <span class="menu_text">
                           	 图表
                            <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                        </span>
                    </a>
                    <ul class="list-group">
                        <li class="list-group-item">
                        	<a href="javascript:;" class="">highChart</a>
                        </li>
                        <li class="list-group-item">
                        	<a href="javascript:;" class="">Pie</a>
                        </li>
                    </ul>
                </li>
                <li class="list-group-item">
                    <a>
                        <span class="glyphicon glyphicon-edit"></span>
                        <span class="menu_text">
                           	 表单
                            <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                        </span>
                    </a>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="javascript:;" class="">表单组件</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="">
                            	向导提示 & 验证
                            	<span class="glyphicon glyphicon-chevron-down pull-right"></span>
                            </a>
                            <ul class="list-group">
                            	<li class="list-group-item">
                            		<a href="javascript:;" data-ajax="true">nice Validate</a>
                            	</li>
                            </ul>
                        </li>
                        <li class="list-group-item">
                            <a class="">编辑器
                            	<span class="glyphicon glyphicon-chevron-down pull-right"></span>
                            </a>
                            <ul class="list-group">
                                <li class="list-group-item">
                                	 <a href="../frontend/simditor" data-ajax="true">Simdiotr</a>
                                </li>
                            </ul>
                        </li>
                        <li class="list-group-item">
                            <a class="">文件上传
                             <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                            </a>
                            <ul class="list-group">
                            	<li class="list-group-item">
                            		<a href="../backend/base-upload.html" data-ajax="true">基本文件上传</a>
                            	</li>
                            	<li class="list-group-item">
                            		<a href="../backend/jquery-upload.html" data-ajax="true">jqUpload文件上传</a>
                            	</li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="list-group-item">
                    <a>
                        <span class="glyphicon glyphicon-calendar"></span>
                        <span class="menu_text">
                           	 日期
                        </span>
                        <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                    </a>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="javascript:;" class="">DateTime Picker</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="">Bootstrap Datepicker</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="">My97 DatePicker</a>
                        </li>
                    </ul>
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
                            <a href="javascript:;" class="">登录</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="">注册</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="">404</a>
                        </li>
                        <li class="list-group-item">
                            <a href="javascript:;" class="">500</a>
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

<script src="${pageContext.request.contextPath }/resources/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/jquery-layer/layer.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/laycode/laycode.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/common/ztool.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/webone/alink.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/webone/menu.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/webone/index.js"></script>
</body>
</html>