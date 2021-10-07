@import app.sjws.IndexPage
@param java.util.Map<String, Object> model
!{IndexPage page =  (IndexPage) model.get("page");}

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SJWS</title>
</head>
<body>
<h1>${page.getGreetings()}</h1>
</body>
</html>
