const express = require('express');
const cors = require('cors')
var corsOptions = {
  origin: `${process.env.VUE_APP_BASE_URL}`,
  optionsSuccessStatus: 200 // some legacy browsers (IE11, various SmartTVs) choke on 204
}
const serveStatic = require("serve-static")
const path = require('path');
const app = express();
app.use(serveStatic(path.join(__dirname, 'dist')));
const port = process.env.PORT || 3000;
app.listen(port, () => {
    console.log('hello world!')
});
app.use(cors(corsOptions))
