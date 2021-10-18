const Action = require('./model/action');

class MyStrategy {
    async getAction(game) {
        return new Action([], [], null);
    }
}

module.exports.MyStrategy = MyStrategy;
