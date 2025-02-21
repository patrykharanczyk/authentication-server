CREATE TABLE if not exists client (
  id varchar(255) NOT NULL,
  client_Id varchar(255) NOT NULL,
  client_Id_Issued_At timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
  client_Secret varchar(255) DEFAULT NULL,
  client_Secret_Expires_At timestamp DEFAULT NULL,
  client_Name varchar(255) NOT NULL,
  client_Authentication_Methods varchar(1000) NOT NULL,
  authorization_Grant_Types varchar(1000) NOT NULL,
  redirect_Uris varchar(1000) DEFAULT NULL,
  post_Logout_Redirect_Uris varchar(1000) DEFAULT NULL,
  scopes varchar(1000) NOT NULL,
  client_Settings varchar(2000) NOT NULL,
  token_Settings varchar(2000) NOT NULL,
  PRIMARY KEY (id)
);