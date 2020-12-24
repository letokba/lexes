// 利用JS中的 eval() 方法验证结果

const fp = require('fs')

let buf = new Buffer.alloc(1024);

fp.open('samples.txt', 'r+', function (err, fd) {
    if(err) {
        return console.error(err);
    }
    fp.read(fd, buf, 0, buf.length, 0, function (err, bytes, buffer){
        if(err) {
            return console.error(err);
        }

        let text = buffer.toString();
        text.replace(/(\n[\s|\t]*\r*\n)/g, '\n')
            .split('\n')
            .map(value => value.trim())
            .forEach(value => {
                try {
                    let eval = global.eval(value);
                    // console.log(`${value}  = ${eval}`)
                    console.log(`${eval}`)
                }catch (e) {
                    console.error(value);
                }
            });
    });
    // console.log("open success");
})

