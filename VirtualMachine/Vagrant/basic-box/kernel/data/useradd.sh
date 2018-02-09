#!/bin/bash
set -Ceu

USER="ocome"
# password "vagrant" を SHA-512 でハッシュ化
COMMAND='print crypt('"${USER}"', "\$6\$");'
PASSWORD=$(perl -e "${COMMAND}")

create_user(){

  echo "Create a user named ${USER}."
  # Create User
  sudo useradd -p ${PASSWORD} -m ${USER}
  # grant authrity
  sudo usermod -G sudo ${USER}
  exit 0
}

trap "create_user" ERR
# check exist user
getent passwd ${USER}

echo "${USER} is already created."