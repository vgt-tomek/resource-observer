<%@tag description="product" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@attribute name="action" required="true" type="java.lang.String"%>

<div class="container-fluid">
    <form method="post" action="${action}">

        <div class="form-group">
            <label for="createResourceFormName">Name:</label>
            <input id="createResourceFormName" class="form-control" type="text" name="name" value="" />
        </div>

        <div class="form-group">
            <label for="createResourceFormUrl">Url:</label>
            <input id="createResourceFormUrl" class="form-control" type="text" name="url" value="" />
        </div>

        <div class="form-group">
            <label for="createResourceFormCheckInterval">Check interval [minutes]:</label>
            <input id="createResourceFormCheckInterval" class="form-control" type="text" name="url" value="" />
        </div>

        <div class="form-group">
            <label for="createResourceFormActive">Active:</label>
            <input id="createResourceFormActive" class="checkbox" type="checkbox" name="active" />
        </div>

        <div class="form-group form-submit-buttons">
            <input id="productFormSubmit" class="btn btn-default btn-nav" type="submit" name="resourceFormSubmit" value="Save" />
            <input id="productFormCancel" class="btn btn-default btn-nav" type="submit" name="resourceFormCancel" value="Cancel" />
        </div>

    </form>
</div>
