class Pimage < ActiveRecord::Base
belongs_to :Product, counter_cache: true
end
