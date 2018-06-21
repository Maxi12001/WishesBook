class CreateDatabase < ActiveRecord::Migration[5.2]
  def self.up
    load 'schema.rb'
  end

  def self.down
    # drop all the tables if you really need
    # to support migration back to version 0
  end
end
