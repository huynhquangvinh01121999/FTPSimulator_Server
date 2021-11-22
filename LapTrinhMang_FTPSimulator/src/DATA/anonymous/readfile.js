const fs = require("fs");

var data = JSON.parse(fs.readFileSync("MockData/data_xvideos.json", "utf8"));
console.log(`Total item: ${data.length}`);
