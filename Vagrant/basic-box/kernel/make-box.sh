
# 删除旧的box
rm -rf ubuntu16.box

vagrant package --output ubuntu16.box

# 强制更新
vagrant box add --force huiwq1990/ubuntu16 ubuntu16.box
