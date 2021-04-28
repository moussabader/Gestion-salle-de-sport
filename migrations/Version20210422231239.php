<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210422231239 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE lignecommande');
        $this->addSql('ALTER TABLE avis DROP FOREIGN KEY fk_avis_pr');
        $this->addSql('ALTER TABLE avis CHANGE id_produit id_produit INT DEFAULT NULL');
        $this->addSql('ALTER TABLE avis ADD CONSTRAINT FK_8F91ABF0F7384557 FOREIGN KEY (id_produit) REFERENCES produit (id_produit)');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY fk_cmd_cl');
        $this->addSql('ALTER TABLE commande CHANGE id_c id_c INT DEFAULT NULL');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67DC12C7510 FOREIGN KEY (id_c) REFERENCES client (id_c)');
        $this->addSql('ALTER TABLE cours ADD rate DOUBLE PRECISION DEFAULT NULL, ADD vote INT DEFAULT NULL');
        $this->addSql('ALTER TABLE produit CHANGE quantite_commande quantite_commande INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY fk_rec_co');
        $this->addSql('ALTER TABLE reclamation CHANGE id_co id_co INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE6064041279AF55 FOREIGN KEY (id_co) REFERENCES coach (id_co)');
        $this->addSql('ALTER TABLE reservations DROP FOREIGN KEY fk_res_cl');
        $this->addSql('ALTER TABLE reservations DROP FOREIGN KEY fk_res_cours');
        $this->addSql('ALTER TABLE reservations CHANGE id_user id_user INT DEFAULT NULL, CHANGE id_cours id_cours INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reservations ADD CONSTRAINT FK_4DA2396B3CA4B FOREIGN KEY (id_user) REFERENCES client (id_c)');
        $this->addSql('ALTER TABLE reservations ADD CONSTRAINT FK_4DA239134FCDAC FOREIGN KEY (id_cours) REFERENCES cours (id)');
        $this->addSql('ALTER TABLE vote DROP FOREIGN KEY vote_ibfk_1');
        $this->addSql('ALTER TABLE vote DROP FOREIGN KEY vote_ibfk_2');
        $this->addSql('ALTER TABLE vote CHANGE id_user id_user INT DEFAULT NULL, CHANGE id_cours id_cours INT DEFAULT NULL');
        $this->addSql('ALTER TABLE vote ADD CONSTRAINT FK_5A108564134FCDAC FOREIGN KEY (id_cours) REFERENCES cours (id)');
        $this->addSql('ALTER TABLE vote ADD CONSTRAINT FK_5A1085646B3CA4B FOREIGN KEY (id_user) REFERENCES client (id_c)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE lignecommande (id_commande INT NOT NULL, id_produit INT NOT NULL, nom_produit VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, quantite_commande INT NOT NULL, nom_client VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, UNIQUE INDEX uk_cmd_pr (id_commande, id_produit), INDEX fk_ligne_pr (id_produit), INDEX IDX_853B79393E314AE8 (id_commande)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE lignecommande ADD CONSTRAINT fk_ligne_cmd FOREIGN KEY (id_commande) REFERENCES commande (id_commande) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE lignecommande ADD CONSTRAINT fk_ligne_pr FOREIGN KEY (id_produit) REFERENCES produit (id_produit) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE avis DROP FOREIGN KEY FK_8F91ABF0F7384557');
        $this->addSql('ALTER TABLE avis CHANGE id_produit id_produit INT NOT NULL');
        $this->addSql('ALTER TABLE avis ADD CONSTRAINT fk_avis_pr FOREIGN KEY (id_produit) REFERENCES produit (id_produit) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67DC12C7510');
        $this->addSql('ALTER TABLE commande CHANGE id_c id_c INT NOT NULL');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT fk_cmd_cl FOREIGN KEY (id_c) REFERENCES client (id_c) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE cours DROP rate, DROP vote');
        $this->addSql('ALTER TABLE produit CHANGE quantite_commande quantite_commande INT DEFAULT 0');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE6064041279AF55');
        $this->addSql('ALTER TABLE reclamation CHANGE id_co id_co INT NOT NULL');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT fk_rec_co FOREIGN KEY (id_co) REFERENCES coach (id_co) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reservations DROP FOREIGN KEY FK_4DA2396B3CA4B');
        $this->addSql('ALTER TABLE reservations DROP FOREIGN KEY FK_4DA239134FCDAC');
        $this->addSql('ALTER TABLE reservations CHANGE id_user id_user INT NOT NULL, CHANGE id_cours id_cours INT NOT NULL');
        $this->addSql('ALTER TABLE reservations ADD CONSTRAINT fk_res_cl FOREIGN KEY (id_user) REFERENCES client (id_c) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reservations ADD CONSTRAINT fk_res_cours FOREIGN KEY (id_cours) REFERENCES cours (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE vote DROP FOREIGN KEY FK_5A108564134FCDAC');
        $this->addSql('ALTER TABLE vote DROP FOREIGN KEY FK_5A1085646B3CA4B');
        $this->addSql('ALTER TABLE vote CHANGE id_cours id_cours INT NOT NULL, CHANGE id_user id_user INT NOT NULL');
        $this->addSql('ALTER TABLE vote ADD CONSTRAINT vote_ibfk_1 FOREIGN KEY (id_cours) REFERENCES cours (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE vote ADD CONSTRAINT vote_ibfk_2 FOREIGN KEY (id_user) REFERENCES client (id_c) ON UPDATE CASCADE ON DELETE CASCADE');
    }
}
