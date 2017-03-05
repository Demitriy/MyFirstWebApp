   <%@ taglib prefix="c"
              uri="http://java.sun.com/jsp/jstl/core" %>

              <c:set var="salary" scope="session" value="${2000*2}"/>
              <c:out value="${salary}"/>


    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.1</version>
      <scope>provided</scope>
    </dependency>



    <table>
    <c:forEach var="tmp" items="${list}">
        <tr>
            <c:forEach var="inner" items="${tmp}">
                <td>
                    <p>${inner}</p>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
    </table>