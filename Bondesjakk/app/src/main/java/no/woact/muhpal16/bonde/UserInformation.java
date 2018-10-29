package no.woact.muhpal16.bonde;

public class UserInformation {


    public String gameTag;

    public UserInformation(String gameTag) {
        this.gameTag = gameTag;
    }


    public String getUserGameTag(){
        gameTag = this.gameTag;
        return gameTag;
    }
}
