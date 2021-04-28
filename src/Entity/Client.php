<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Client
 *
 * @ORM\Table(name="client")
 * @ORM\Entity
 */
class Client
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_c", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idC;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_c", type="string", length=255, nullable=false)
     */
    private $nomC;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom_c", type="string", length=255, nullable=false)
     */
    private $prenomC;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="ddn", type="date", nullable=false)
     */
    private $ddn;

    /**
     * @var string
     *
     * @ORM\Column(name="genre_c", type="string", length=255, nullable=false)
     */
    private $genreC;

    /**
     * @var string
     *
     * @ORM\Column(name="login_c", type="string", length=255, nullable=false)
     */
    private $loginC;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp_c", type="string", length=255, nullable=false)
     */
    private $mdpC;

    public function getIdC(): ?int
    {
        return $this->idC;
    }

    public function getNomC(): ?string
    {
        return $this->nomC;
    }

    public function setNomC(string $nomC): self
    {
        $this->nomC = $nomC;

        return $this;
    }

    public function getPrenomC(): ?string
    {
        return $this->prenomC;
    }

    public function setPrenomC(string $prenomC): self
    {
        $this->prenomC = $prenomC;

        return $this;
    }

    public function getDdn(): ?\DateTimeInterface
    {
        return $this->ddn;
    }

    public function setDdn(\DateTimeInterface $ddn): self
    {
        $this->ddn = $ddn;

        return $this;
    }

    public function getGenreC(): ?string
    {
        return $this->genreC;
    }

    public function setGenreC(string $genreC): self
    {
        $this->genreC = $genreC;

        return $this;
    }

    public function getLoginC(): ?string
    {
        return $this->loginC;
    }

    public function setLoginC(string $loginC): self
    {
        $this->loginC = $loginC;

        return $this;
    }

    public function getMdpC(): ?string
    {
        return $this->mdpC;
    }

    public function setMdpC(string $mdpC): self
    {
        $this->mdpC = $mdpC;

        return $this;
    }


}
