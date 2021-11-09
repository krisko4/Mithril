const express = require('express');
const cors = require('cors')
const serveStatic = require("serve-static")
const path = require('path');
const app = express();
app.use(serveStatic(path.join(__dirname, 'dist')));
app.use(history({
    verbose: true
}))
const port = process.env.PORT || 3000;
app.listen(port, () => {
    console.log('hello world!')
});
app.use(cors())
