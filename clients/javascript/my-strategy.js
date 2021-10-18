const Action = require('./model/action');

class MyStrategy {
    async getAction(game) {
        return new Action([], []);
    }
}

module.exports.MyStrategy = MyStrategy;
