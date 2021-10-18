require_relative 'model'

class MyStrategy
    def get_action(game)
        return Model::Action.new([], [], nil)
    end
end