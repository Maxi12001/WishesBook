module Api
  module V1
    class SigninController< ApplicationController
      def index
        s=Session.find_by(SID: params[:SID])
        if s!=nil
        render json:{status: 'succes',msg:'Valid Session',data: s}, status: :ok
        else
        render json:{status: 'succes',msg:'not a Valid Session'}, status: :ok
        end
      end
      def create
        x=Users.find_by(Email: params[:Mail])
        passrd =Digest::MD5.hexdigest(params[:passWord])
        if x.pass==passrd
          sID=SecureRandom.random_number(1100000011)
          nSession=Session.create(UID: x.UID,SID: sID)
          render json:{status: 'succes',msg:'Valid Email and passWord',data: nSession}, status: :ok
        else
          render json:{status: 'succes',msg:'not a Valid mail or password'}, status: :ok
        end
      end
end
  end
    end
