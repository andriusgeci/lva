package Command;

public class CommandFactory {

    public Command createCommand(String commandStr) {
        Command command = null;
        //Instantiate the required Command object...
        switch (commandStr) {
            case "login":
                command = new LoginCommand();
                break;
            case "register":
                command = new RegisterCommand();
                break;
            case "forgotPass":
                command = new ForgotPasswordCommand();
                break;
            case "changePassword":
                command = new ChangePasswordCommand();
                break;
            case "movieDetails":
                command = new MovieDetailsCommand();
                break;
            case "reviewDetails":
                command = new ReviewDetailsCommand();
                break;
            case "addToCart":
                command = new CartCommand();
                break;
            case "delFromCart":
                command = new DelFromCartCommand();
                break;
            case "byGenre":
                command = new ByGenreCommand();
                break;
            case "sorting":
                command = new SortSelectCommand();
                break;
            case "sliderValues":
                command = new SliderCommand();
                break;
            case "allMovies":
                command = new AllMoviesCommand();
                break;
            case "orderDetails":
                command = new OrderDetailsCommand();
                break;
            case "Checkout":
                command = new CheckoutCommand();
                break;
            case "proceedToPaypal":
                command = new ProceedToPaypalCommand();
                break;
            case "editMovieDetails":
                command = new EditMovieDetailsCommand();
                break;
            case "updateMovieDetails":
                command = new UpdateMovieDetailsCommand();
                break;
            case "deleteMovie":
                command = new DeleteMovieCommand();
                break;
            case "addMovie":
                command = new AddMovieCommand();
                break;
            case "addUser":
                command = new AddUserCommand();
                break;
            case "deleteUser":
                command = new DeleteUserCommand();
                break;
            case "editUserDetails":
                command = new EditUserDetailsCommand();
                break;
            case "updateUserDetails":
                command = new UpdateUserDetailsCommand();
                break;
            case "updateName":
                command = new UpdateNameCommand();
                break;
            case "updateLastName":
                command = new UpdateLastNameCommand();
                break;
            case "updateEmail":
                command = new UpdateEmailCommand();
                break;
            case "editPassword":
                command = new editPasswordCommand();
                break;
            case "updateAddress":
                command = new updateAddressCommand();
                break;
            case "updateAddressDetails":
                command = new UpdateAddressDetailsCommand();
                break;
            case "delReview":
                command = new DelReviewCommand();
                break;
            case "deleteAddress":
                command = new DeleteAddressCommand();
                break;
            case "addAddress":
                command = new AddAddressCommand();
                break;
            default:
                command = new ErrorCommand();
                break;
        }
        //Return the instantiated Command object to the calling code...
        return command;
    }
}
