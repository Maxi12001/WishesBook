module Api
  module V1
    class ProductController< ApplicationController
      def index
        #s=Product.select("products.PID,products.DSC,Products.price,Products.inStock,Pimages.image").joins("INNER Join Pimages on products.PID = Pimages.product_id")
        s=Product.select("products.PID,products.DSC,Products.price,Products.inStock")
        render json:{status: 'succes',data: s,class: s.class,d: s}, status: :ok
      end
      def show
        s=Product.select("products.PID,products.DSC,Products.price,Products.inStock").where('Products.CatID=?',params[:id].to_i)
        render json: s, status: :ok
      end
    end
      end
        end
