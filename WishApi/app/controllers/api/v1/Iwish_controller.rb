module Api
  module V1
    class IwishController< ApplicationController
      def create
        s=Iwish.create(users_id: params[:UID].to_i,Wish: params[:wtxt],link: params[:link])
        s.errors.full_messages
          render json: s.errors.full_messages, status: :ok
      end
      def show
        s=Iwish.select("WID","Wish").where('users_id=?',params[:id].to_i)
        render json: s, status: :ok
      end
    end
  end
end
