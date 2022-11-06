package boundary;

import entity.MovieGoer;
import global.Constants;
import global.UserSession;

public class TransactionHistoryView {
	
	public static void check_login_before_transaction_view()
	{
		if (UserSession.movieGoer == null) {
			System.out.println("Please login before booking a movie! Directing you to Login Screen..");
			System.out.println();
			UserSession.movieGoer = (MovieGoer) new LoginView(Constants.MOVIE_GOER).showLoginView();
			if(UserSession.movieGoer == null)
			{
				return;
			}
			transaction_history_view();
		} else {
			transaction_history_view();
		}
	}
	
	public static void transaction_history_view()
	{
		
	}
}
