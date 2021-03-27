<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Catalog items</title></head>
<body>
<main>
    <div>
        <h4>The catalogue is situated at path:</h4><em>${catalogPath}</em>
        <h4>the content of the catalog:</h4>
        <ul>
            <#list itemList as item>
                <li> ${item.id}, ${item.name}</li>
            </#list>
        </ul>
    </div>

</main>

</body>
</html>