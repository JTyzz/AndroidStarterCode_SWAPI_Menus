package com.lambdaschool.swapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Planet extends SwApiObject
{
    protected String edited;
    protected String terrain;
    protected String diameter;
    protected String[] films;
    protected String url;
    protected String surface_water;
    protected String orbital_period;
    protected String created;
    protected String rotation_period;
    protected String climate;
    protected String gravity;
    protected String population;
    protected String[] residents;

    public Planet(String edited, String terrain, String diameter, String[] films, String url, String surface_water, String orbital_period, String created, String rotation_period, String climate, String gravity, String population, String[] residents, String name) {
        this.edited = edited;
        this.terrain = terrain;
        this.diameter = diameter;
        this.films = films;
        this.url = url;
        this.surface_water = surface_water;
        this.orbital_period = orbital_period;
        this.created = created;
        this.rotation_period = rotation_period;
        this.climate = climate;
        this.gravity = gravity;
        this.population = population;
        this.residents = residents;
        super.name = name;
        super.parseUrlForId(this.url);
    }

    public Planet(JSONObject json) {
        try {
            this.edited = json.getString("edited");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.terrain = json.getString("terrain");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.diameter = json.getString("diameter");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonFilms = json.getJSONArray("films");
            this.films = new String[jsonFilms.length()];
            for(int i = 0; i < jsonFilms.length(); ++i) {
                this.films[i] = jsonFilms.getString(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.url = json.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.surface_water = json.getString("surface_water");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.orbital_period = json.getString("orbital_period");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.created = json.getString("created");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            super.name = json.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.rotation_period = json.getString("rotation_period");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.climate = json.getString("climate");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.gravity = json.getString("gravity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.population = json.getString("population");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray residentsArray = json.getJSONArray("residents");
            this.residents = new String[residentsArray.length()];
            for(int i = 0; i < residentsArray.length(); ++i) {
                this.residents[i] = residentsArray.getString(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.parseUrlForId(this.url);
    }

    public String getEdited()
    {
        return edited;
    }

    public void setEdited (String edited)
    {
        this.edited = edited;
    }

    public String getTerrain ()
    {
        return terrain;
    }

    public void setTerrain (String terrain)
    {
        this.terrain = terrain;
    }

    public String getDiameter ()
    {
        return diameter;
    }

    public void setDiameter (String diameter)
    {
        this.diameter = diameter;
    }

    public String[] getFilms ()
    {
        return films;
    }

    public void setFilms (String[] films)
    {
        this.films = films;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getSurface_water ()
    {
        return surface_water;
    }

    public void setSurface_water (String surface_water)
    {
        this.surface_water = surface_water;
    }

    public String getOrbital_period ()
    {
        return orbital_period;
    }

    public void setOrbital_period (String orbital_period)
    {
        this.orbital_period = orbital_period;
    }

    public String getCreated ()
    {
        return created;
    }

    public void setCreated (String created)
    {
        this.created = created;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        super.name = name;
    }

    public String getRotation_period ()
    {
        return rotation_period;
    }

    public void setRotation_period (String rotation_period)
    {
        this.rotation_period = rotation_period;
    }

    public String getClimate ()
    {
        return climate;
    }

    public void setClimate (String climate)
    {
        this.climate = climate;
    }

    public String getGravity ()
    {
        return gravity;
    }

    public void setGravity (String gravity)
    {
        this.gravity = gravity;
    }

    public String getPopulation ()
    {
        return population;
    }

    public void setPopulation (String population)
    {
        this.population = population;
    }

    public String[] getResidents ()
    {
        return residents;
    }

    public void setResidents (String[] residents)
    {
        this.residents = residents;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [edited = "+edited+", terrain = "+terrain+", diameter = "+diameter+", films = "+films+", url = "+url+", surface_water = "+surface_water+", orbital_period = "+orbital_period+", created = "+created+", name = "+name+", rotation_period = "+rotation_period+", climate = "+climate+", gravity = "+gravity+", population = "+population+", residents = "+residents+"]";
    }
}
