$script = <<-SCRIPT
apt-get update -qq
apt install -y openjdk-8-jdk zip unzip
su - vagrant -c 'curl -s "https://get.sdkman.io" | bash'
su - vagrant -c 'source $HOME/.sdkman/bin/sdkman-init.sh && sdk install gradle 4.10.2'
SCRIPT

Vagrant.configure("2") do |config|
  config.vm.box = "bento/ubuntu-16.04"
  config.vm.provision "shell", inline: $script
end
