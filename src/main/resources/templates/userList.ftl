<#import "parts/common.ftl" as i>
<@i.page>
<body>
<p >
    List of Users
<table>
    <thead>
    <tr>
        <th>
            Name
        </th>
        <th>
            Role
        </th>
        <th>

        </th>
    </tr>
    </thead>
    <tbody>

    </tbody>
    <#list users as user>
    <tr>
        <td>${user.username}</td>
        <td><#list user.roles as role> ${role}<#sep>, </#list></td>
        <td><a href="/user/${user.id}">edit</a></td>
    </tr>
    <#else>
    No users
</#list>
</table>
</p>
</body>
</@i.page>