<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Coach
 *
 * @ORM\Table(name="coach")
 * @ORM\Entity
 */
class Coach
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_co", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idCo;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_co", type="string", length=255, nullable=false)
     */
    private $nomCo;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom_co", type="string", length=255, nullable=false)
     */
    private $prenomCo;

    /**
     * @var string
     *
     * @ORM\Column(name="genre_co", type="string", length=255, nullable=false)
     */
    private $genreCo;

    /**
     * @var string
     *
     * @ORM\Column(name="login_co", type="string", length=255, nullable=false)
     */
    private $loginCo;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp_co", type="string", length=255, nullable=false)
     */
    private $mdpCo;

    public function getIdCo(): ?int
    {
        return $this->idCo;
    }

    public function getNomCo(): ?string
    {
        return $this->nomCo;
    }

    public function setNomCo(string $nomCo): self
    {
        $this->nomCo = $nomCo;

        return $this;
    }

    public function getPrenomCo(): ?string
    {
        return $this->prenomCo;
    }

    public function setPrenomCo(string $prenomCo): self
    {
        $this->prenomCo = $prenomCo;

        return $this;
    }

    public function getGenreCo(): ?string
    {
        return $this->genreCo;
    }

    public function setGenreCo(string $genreCo): self
    {
        $this->genreCo = $genreCo;

        return $this;
    }

    public function getLoginCo(): ?string
    {
        return $this->loginCo;
    }

    public function setLoginCo(string $loginCo): self
    {
        $this->loginCo = $loginCo;

        return $this;
    }

    public function getMdpCo(): ?string
    {
        return $this->mdpCo;
    }

    public function setMdpCo(string $mdpCo): self
    {
        $this->mdpCo = $mdpCo;

        return $this;
    }


}
