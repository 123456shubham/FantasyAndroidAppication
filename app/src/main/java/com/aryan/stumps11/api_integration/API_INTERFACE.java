package com.aryan.stumps11.api_integration;

import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyDummyTeamData;
import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyDummyTeamPlayer11;
import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyDummyTeamRes;
import com.aryan.stumps11.ApiModel.profile.EditProfile.EditProfileReq;
import com.aryan.stumps11.ApiModel.profile.EditProfile.EditProfileRes;
import com.aryan.stumps11.ApiModel.profile.addBank.AddBankRequest;
import com.aryan.stumps11.ApiModel.profile.addBank.AddBankResponse;
import com.aryan.stumps11.ApiModel.profile.addBank.GetBankAccountDetails;
import com.aryan.stumps11.ApiModel.profile.addMoney.AddMoneyRequest;
import com.aryan.stumps11.ApiModel.profile.addMoney.AddMoneyResponse;
import com.aryan.stumps11.ApiModel.profile.banner.BannerResponse;
import com.aryan.stumps11.ApiModel.profile.context.ContextResponse;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateReqData;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateTeamReq;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateTeamResponse;
import com.aryan.stumps11.ApiModel.profile.documents.UserDocumentsResponse;
import com.aryan.stumps11.ApiModel.profile.dummyCreateRes.DummyResponse;
import com.aryan.stumps11.ApiModel.profile.elevenPlayer.ElevenPlayerRes;
import com.aryan.stumps11.ApiModel.profile.kyc.KycRequest;
import com.aryan.stumps11.ApiModel.profile.kyc.KycResponse;
import com.aryan.stumps11.ApiModel.profile.profilegetProfile.ProfileRequest;
import com.aryan.stumps11.ApiModel.profile.profilegetProfile.ProfileResponse;
import com.aryan.stumps11.ApiModel.profile.rank.RankData;
import com.aryan.stumps11.ApiModel.profile.rank.RankResponse;
import com.aryan.stumps11.ApiModel.profile.refercode.EnterReferCodeRequest;
import com.aryan.stumps11.ApiModel.profile.refercode.EnterReferCodeResponse;
import com.aryan.stumps11.ApiModel.profile.refercode.GetMoneyResponse;
import com.aryan.stumps11.ApiModel.profile.refercode.GetReferCodeResponse;
import com.aryan.stumps11.ApiModel.profile.transaction.MyTransactionResponse;
import com.aryan.stumps11.ApiModel.profile.verifyAccount.VerifyAccountResponse;
import com.aryan.stumps11.ApiModel.profile.wallet.WalletResponse;
import com.aryan.stumps11.ApiModel.profile.withdraw.AmountWithdrawResponse;
import com.aryan.stumps11.ApiModel.profile.withdraw.WithDrawMoneyReq;
import com.aryan.stumps11.ContestClick.lederboard_details.LeaderBoardResponse;
import com.aryan.stumps11.Signup.model.Data;
import com.aryan.stumps11.Signup.model.MobileRequest;
import com.aryan.stumps11.Signup.model.MobileResponse;
import com.aryan.stumps11.Signup.model.MobileVerfiyOTPRequest;
import com.aryan.stumps11.Signup.model.MobileVerifyOtpResponse;
import com.aryan.stumps11.Winners.winnerRes.WinnerResponse;
import com.aryan.stumps11.comingMatch.ComingSoonDetails;
import com.aryan.stumps11.comingMatch.CommingSoonResponse;
import com.aryan.stumps11.comingMatch.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_INTERFACE {

    // 1) login with mobile Number
    @POST("/api/auth/otp/login")
    Call<Data> getAllData(@Body MobileRequest mobileRequest);

    // 2) VerifyOTP
    @POST("/api/auth/otp-verfiy")
    Call<MobileVerifyOtpResponse> verifyOTP(@Body MobileVerfiyOTPRequest mobileVerfiyOTPRequest);

    // 3) get Profile
    @GET("/api/user/profile/details")
    Call<ProfileResponse> getProfile(@Header("Authorization")String token);

    // 4) update Profile
    @PUT("/api/user/profile/update")
    Call<EditProfileRes> updateProfile(@Header("Authorization") String token, @Body EditProfileReq editProfileReq);

    // 5) Wallet Balance
    @GET("/api/user/wallet")
    Call<WalletResponse> getWalletBalance(@Header("Authorization") String token);

    // 6) add money from wallet
    @POST("/api/user/add-payment")
    Call<AddMoneyResponse> addMoney(@Header("Authorization") String token, @Body AddMoneyRequest addMoneyRequest);

    // 7) enter refer code
    @POST("/api/user/refercode")
    Call<EnterReferCodeResponse> enterReferCode(@Header("Authorization") String token,@Body EnterReferCodeRequest enterReferCodeRequest);

    // 8) get refer code
    @GET("/api/user/shared-url")
    Call<GetReferCodeResponse> getRefferCode(@Header("Authorization") String token);

    // 9) send money when user enter refer code
    @GET("/api/user/shared-details")
    Call<GetMoneyResponse> sendMoney(@Header("Authorization") String token);

    // 10) Add Bank Account Details
    @POST("/api/user/add-bank-details")
    Call<AddBankResponse> addBankDetails(@Header("Authorization") String token , @Body AddBankRequest addBankRequest);

    // 11) KYC Documents
    @POST("api/user/document/upload/pancard")
    Call<KycResponse> addPancardDetails(@Header("Authorization") String token, @Body KycRequest kycRequest);

    // 12) KYC USER Documents
    @GET("/api/user/documents")
    Call<UserDocumentsResponse> getDocumentsDetails(@Header("Authorization") String token);

    // 13) get All Transaction
    @GET("/api/user/transaction")
    Call<MyTransactionResponse> getAllTransaction(@Header("Authorization") String token);

    // 14) Verify Account Number
    @GET("/api/user/check/bank-details")
    Call<VerifyAccountResponse> verifyBankAccount(@Header("Authorization") String token);

    //15) Get Bank Details
    @GET("/api/user/bank-details")
    Call<GetBankAccountDetails> getBankAccount(@Header("Authorization") String token);

    // 16) Withdraw money
    @POST("/api/user/withdraw")
    Call<AmountWithdrawResponse> getWithdraw(@Header("Authorization") String token, @Body WithDrawMoneyReq withDrawMoneyReq);

    // 17) coming soon match
    @GET("/v2/matches/?status=1&pre_squad=false&per_page=176&token=de7cddfd6309f89136ca5c4f68aaff99")
    Call <CommingSoonResponse> getComingSoonMatch();

    // 18) contxt api

    @GET("/api/user/contest/list/{match_id}")
    Call<ContextResponse> getContest(@Header("Authorization") String token,@Path("match_id") String matchid);

    // 19) Rank api
    @GET("api/user/contest/rank/{rank_id}")
    Call <RankResponse> getRankResponse(@Header("Authorization") String token, @Path("rank_id") String rankId);

    // 20) create team api
    @POST("/api/user/iteam/create")
    Call<CreateTeamResponse> createTeamPlayer11(@Header("Authorization") String token, @Body CreateTeamReq createTeamReq);
    // 21) Banner Slider
    @GET("/api/user/banner/list")
    Call<BannerResponse> getBannerList(@Header("Authorization") String token);

    // 22) store data in local server
    @POST("/api/user/team/temp/create")
    Call<DummyResponse> creteTeamTempData(@Header("Authorization") String token, @Body CreateTeamReq createTeamReq);

    // 23) get data in local server
    @GET("/api/user/team/temp/list")
    Call<MyDummyTeamRes> getDummyTeam(@Header("Authorization") String token);

    // 24 ) slected 11 player list

    @GET("/api/user/team/temp/details/{user_Id}")
    Call<ElevenPlayerRes> getSelected11Player(@Header("Authorization" )String token, @Path("user_Id") String userId);

    //25 ) winner List api
    @GET("/api/user/winner/contest/list")
    Call<WinnerResponse> getWinnerList(@Header("Authorization") String token);

    // 26) Leader Board api
    @GET("/api/user/contest/leaderboard/{match_id}")
    Call<LeaderBoardResponse> getLeaderBoard(@Header("Authorization") String token, @Path("match_id") String match);

    //27) add player 11
    @PUT("api/user/team/temp/update/{user_Id}")
    Call<DummyResponse> addPlayer11(@Header("Authorization") String token, @Body CreateTeamReq createTeamReq);




}
