<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>記事　一覧</h2>
        <ul>
            <c:forEach var="title" items="${titles}">
                <li>
                    <c:choose>
                                <c:when test="${title.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/replys/index?id=${title.id}">
                                        <c:out value="${title.title}"/>
                                    </a>

                                    <a href="${pageContext.request.contextPath}/titles/edit?id=${title.id}">
                                        編集
                                    </a>
                                </c:otherwise>
                            </c:choose>
                </li>
            </c:forEach>
        </ul>

        <div id="pagination">
            （全 ${titles_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((titles_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/titles/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/titles/new' />">新しい記事を作る</a></p>

    </c:param>
</c:import>