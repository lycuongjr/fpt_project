<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.fpt.entity.Atube" %>
<%@ page import="java.util.List" %><%--
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ngoly
  Date: 18/07/2019
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Tube</h1>
<h3>Number Format:</h3>
<c:set var = "balance" value = "120000.2309" scope="page"/>

<p>Formatted Number (1): <fmt:formatNumber value = "${balance}"
                                           type = "currency"/></p>

<p>Formatted Number (2): <fmt:formatNumber type = "number"
                                           maxIntegerDigits = "10" maxFractionDigits="2" value = "${balance}" /></p>

<p>Formatted Number (3): <fmt:formatNumber type = "number"
                                           maxFractionDigits = "1" value = "${balance}" /></p>

<p>Formatted Number (4): <fmt:formatNumber type = "number"
                                           groupingUsed = "false" value = "${balance}" /></p>

<p>Formatted Number (5): <fmt:formatNumber type = "percent"
                                           maxIntegerDigits="10" value = "0.23" /></p>

<p>Formatted Number (6): <fmt:formatNumber type = "percent"
                                           minFractionDigits = "10" value = "${balance}" /></p>

<p>Formatted Number (7): <fmt:formatNumber type = "percent"
                                           maxIntegerDigits = "3" value = "${balance}" /></p>

<p>Formatted Number (8): <fmt:formatNumber type = "number"
                                           pattern = "###.###" value = "1245666.2345" /></p>

<p>Currency in USA :
    <fmt:setLocale value = "vi_VN" scope="page"/>
    <fmt:formatNumber value = "${balance}" type = "currency"/>
</p>

</body>
</html>
