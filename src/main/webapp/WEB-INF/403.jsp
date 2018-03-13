<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h1>HTTP Status 403 - Access is denied</h1>
<h2>You do not have permission to access this page!</h2>

<form action="/logout" method="post">
    <input type="submit"  value="Sign in as different user" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>