class CreateDatabase < ActiveRecord::Migration[5.2]
  def self.up
    ActiveRecord::Schema.define(version: 1) do

      create_table "bonus", id: false, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "UID", null: false
        t.integer "Bonus", null: false
        t.index ["UID"], name: "UID"
      end

      create_table "customerop", primary_key: "CPID", id: :integer, default: nil, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "CID", null: false
        t.integer "PC", null: false
        t.integer "OID", null: false
        t.index ["CID"], name: "customerop_ibfk_1"
        t.index ["OID"], name: "OID"
        t.index ["PC"], name: "PC"
      end

      create_table "friend", id: false, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "CID1", null: false
        t.integer "CID2", null: false
        t.boolean "confirmed", null: false
        t.index ["CID1"], name: "CID1"
        t.index ["CID2"], name: "CID2"
      end

      create_table "iwish", id: false, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "UID", null: false
        t.text "Wish", null: false
        t.binary "image", null: false
        t.index ["UID"], name: "UID"
      end

      create_table "order", primary_key: "OID", id: :integer, default: nil, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "fromU", null: false
        t.integer "to", null: false
        t.date "orderDate", null: false
        t.index ["fromU"], name: "fromU"
        t.index ["to"], name: "to"
      end

      create_table "orderitem", id: false, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "OID", null: false
        t.integer "PID", null: false
        t.index ["OID"], name: "OID"
        t.index ["PID"], name: "PID"
      end

      create_table "orderstatue", id: false, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "OID", null: false
        t.integer "SID", null: false
        t.date "diliverDate", null: false
        t.index ["OID"], name: "OID"
        t.index ["SID"], name: "SID"
      end

      create_table "pimage", id: false, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "PID", null: false
        t.binary "Image", null: false
        t.index ["PID"], name: "PID"
      end

      create_table "product", primary_key: "PID", id: :integer, default: nil, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "CatID", null: false
        t.text "DSC", null: false
        t.integer "inStock", default: 0, null: false
        t.index ["CatID"], name: "CatID"
      end

      create_table "productat", id: false, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "RID", null: false
        t.integer "PID", null: false
        t.decimal "price", precision: 5, scale: 2, null: false
        t.integer "inStock", null: false
        t.index ["PID"], name: "PID"
        t.index ["RID"], name: "RID"
      end

      create_table "productcat", primary_key: "CID", id: :integer, default: nil, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "ParentCID"
        t.string "Name", limit: 20, null: false
        t.index ["ParentCID"], name: "ParentCID"
      end

      create_table "refpayment", primary_key: "RefID", id: :integer, default: nil, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.text "Name", null: false
      end

      create_table "saler", primary_key: "Rid", id: :integer, default: nil, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT", force: :cascade do |t|
        t.text "Name", null: false
        t.float "rate", null: false
        t.integer "noOfrate", null: false
      end

      create_table "sessions", id: false, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "UID", null: false
        t.integer "SID", null: false
      end

      create_table "shipmentstatue", primary_key: "SID", id: :integer, default: nil, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.text "dsc", null: false
      end

      create_table "users", primary_key: "UID", id: :integer, default: nil, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT", force: :cascade do |t|
        t.string "fName", limit: 15, null: false
        t.string "lName", limit: 15
        t.string "Email", limit: 35, null: false
        t.text "pass", null: false
        t.integer "pNo"
        t.date "Bdate", null: false
        t.text "address1"
        t.text "address2"
        t.binary "PImamge"
        t.string "town", limit: 10
        t.string "government", limit: 15
        t.boolean "active", null: false
        t.timestamp "REgisterDate", default: -> { "CURRENT_TIMESTAMP" }, null: false
        t.index ["Email"], name: "Email", unique: true
        t.index ["pNo"], name: "pNo", unique: true
      end

      create_table "wishlist", id: false, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
        t.integer "UID", null: false
        t.integer "PID", null: false
        t.boolean "ordered", null: false
        t.index ["PID"], name: "PID"
        t.index ["UID"], name: "wishlist_ibfk_1"
      end

      add_foreign_key "bonus", "users", column: "UID", primary_key: "UID", name: "bonus_ibfk_1", on_update: :cascade
      add_foreign_key "customerop", "order", column: "OID", primary_key: "OID", name: "customerop_ibfk_3"
      add_foreign_key "customerop", "refpayment", column: "PC", primary_key: "RefID", name: "customerop_ibfk_2", on_update: :cascade
      add_foreign_key "customerop", "users", column: "CID", primary_key: "UID", name: "customerop_ibfk_1", on_update: :cascade
      add_foreign_key "friend", "users", column: "CID1", primary_key: "UID", name: "friend_ibfk_1", on_update: :cascade
      add_foreign_key "friend", "users", column: "CID2", primary_key: "UID", name: "friend_ibfk_2", on_update: :cascade
      add_foreign_key "iwish", "users", column: "UID", primary_key: "UID", name: "iwish_ibfk_1"
      add_foreign_key "order", "users", column: "fromU", primary_key: "UID", name: "order_ibfk_1", on_update: :cascade
      add_foreign_key "order", "users", column: "to", primary_key: "UID", name: "order_ibfk_2", on_update: :cascade
      add_foreign_key "orderitem", "order", column: "OID", primary_key: "OID", name: "orderitem_ibfk_1", on_update: :cascade
      add_foreign_key "orderitem", "product", column: "PID", primary_key: "PID", name: "orderitem_ibfk_2", on_update: :cascade
      add_foreign_key "orderstatue", "order", column: "OID", primary_key: "OID", name: "orderstatue_ibfk_1", on_update: :cascade
      add_foreign_key "orderstatue", "shipmentstatue", column: "SID", primary_key: "SID", name: "orderstatue_ibfk_2", on_update: :cascade
      add_foreign_key "pimage", "product", column: "PID", primary_key: "PID", name: "pimage_ibfk_1"
      add_foreign_key "product", "productcat", column: "CatID", primary_key: "CID", name: "product_ibfk_1", on_update: :cascade
      add_foreign_key "productat", "product", column: "PID", primary_key: "PID", name: "productat_ibfk_2"
      add_foreign_key "productat", "saler", column: "RID", primary_key: "Rid", name: "productat_ibfk_1", on_update: :cascade
      add_foreign_key "productcat", "productcat", column: "ParentCID", primary_key: "CID", name: "productcat_ibfk_1", on_update: :cascade
      add_foreign_key "wishlist", "product", column: "PID", primary_key: "PID", name: "wishlist_ibfk_2", on_update: :cascade
      add_foreign_key "wishlist", "users", column: "UID", primary_key: "UID", name: "wishlist_ibfk_1", on_update: :cascade
      add_foreign_key "sessions", "users", column: "UID", primary_key: "UID", name: "sessions_ibfk_1", on_update: :cascade
    end
end

  def self.down
    # drop all the tables if you really need
    # to support migration back to version 0
  end
end
