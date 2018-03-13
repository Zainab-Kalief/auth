<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<body>
<h1>I made it to admin</h1>
<form action="/logout" method="post">
    <input type="submit"  value="Sign Out" /> <input
        type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>