<#import "parts/common.ftl" as i>

<@i.page>
${message?ifExists}
<h5>${username}</h5>
<form method="post">

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password: </label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Enter password"/>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email: </label>
        <div class="col-sm-6">
            <input type="email" name="email" class="form-control" placeholder="mail@mail.com" value="${email!''}"/>
        </div>
    </div>

<input type="hidden"  name="_csrf" value="${_csrf.token}"/>
<button type="submit" class="btn btn-primary"/>Save</button>
</form>

</@i.page>