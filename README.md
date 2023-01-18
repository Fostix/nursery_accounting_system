1.
cat > pets
cat > pack_animals
cat pets pack_animals > merge_animals
cat merge_animals
mv merge_animals mans_friends

2.
mkdir nursery_accounting_system && cd nursery_accounting_system && git init && mv ../mans_friends . ../pack_animals . ../pets . ../README.md .

3.
https://dev.mysql.com/doc/mysql-apt-repo-quick-guide/en/

wget https://dev.mysql.com/get/mysql-apt-config_0.8.24-1_all.deb
sudo dpkg -i mysql-apt-config_0.8.24-1_all.deb
sudo apt-get update
sudo apt-get install mysql-server

4.
wget https://az764295.vo.msecnd.net/stable/97dec172d3256f8ca4bfb2143f3f76b503ca0534/code_1.74.3-1673284829_amd64.deb
sudo dpkg -i code_1.74.3-1673284829_amd64.deb
sudo dpkg -r code

5.
history


